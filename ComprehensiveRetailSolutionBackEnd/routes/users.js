const express = require("express");
const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
const User = require("../models/users");
const router = express.Router();
const auth = require("../auth");

router.post("/signup", (req, res, next) => {
  let password = req.body.password;
  bcrypt.hash(password, 10, function (err, hash) {
    if (err) {
      let err = new Error("Could not hash!");
      err.status = 500;
      return next(err);
    }
    User.create({
      businessName: req.body.businessName,
      fullName: req.body.fullName,
      gender: req.body.gender,
      country: req.body.country,
      contactNumber: req.body.contactNumber,
      address: req.body.address,
      emailId: req.body.emailId,
      password: hash,
      image: req.body.image,
    })
      .then((user) => {
        let token = jwt.sign({ _id: user._id }, process.env.SECRET);
        res.json({ status: "Signup success!", token: token });
      })
      .catch(next);
  });
});

router.post("/login", (req, res, next) => {
  User.findOne({ emailId: req.body.emailId })
    .then((user) => {
      if (user == null) {
        let err = new Error("User not found!");
        err.status = 401;
        return next(err);
      } else {
        bcrypt
          .compare(req.body.password, user.password)
          .then((isMatch) => {
            if (!isMatch) {
              let err = new Error("Password does not match!");
              err.status = 401;
              return next(err);
            }
            let token = jwt.sign({ _id: user._id }, process.env.SECRET);
            res.json({ status: "Login success!", token: token });
          })
          .catch(next);
      }
    })
    .catch(next);
});

router.get("/me", auth.verifyUser, (req, res, next) => {
  res.json({
    _id: req.user._id,
    businessName: req.user.businessName,
    fullName: req.user.fullName,
    gender: req.user.gender,
    country: req.user.country,
    contactNumber: req.user.contactNumber,
    address: req.user.address,
    emailId: req.user.emailId,
    image: req.user.image,
  });
});

router.put("/me", auth.verifyUser, (req, res, next) => {
  res.json({
    _id: req.user._id,
    businessName: req.user.businessName,
    fullName: req.user.fullName,
    gender: req.user.gender,
    country: req.user.country,
    contactNumber: req.user.contactNumber,
    address: req.user.address,
    emailId: req.user.emailId,
    image: req.user.image,
  });
});

// router.put('/me', auth.verifyUser, (req, res, next) => {
//     User.findByIdAndUpdate(req.user._id, { $set: req.body }, { new: true })
//         .then((user) => {
//             res.json({ _id: user._id, fullName: req.user.fullName, gender: req.user.gender, bloodGroup: req.user.bloodGroup,country: req.user.country, contactNumber: req.user.contactNumber,address: req.user.address,emailId: req.user.emailId,image: req.user.image });
//         }).catch(next);
// });

router.route("/profile").get((req, res, next) => {
  // console.log(req.user);
  User.findById(req.user._id)
    .then(
      (user) => {
        if (user != null) {
          res.statusCode = 200;
          res.setHeader("Content-Type", "application/json");
          res.json(user);
        } else {
          err = new Error("User " + req.user._id + " not found");
          err.status = 404;
          return next(err);
        }
      },
      (err) => next(err)
    )
    .catch((err) => next(err));
});

router.put("/:id", (req, res, next) => {
  User.findByIdAndUpdate(req.params.id, { $set: req.body }, { new: true })
    .then((user) => {
      res.json(user);
    })
    .catch(next);
});

// router.get('/logout', (req, res, next) => {
//     if (req.user) {
//       req.session.destroy();
//       res.clearCookie('session-id');
//       res.send("logout");
//     } else {
//       let err = new Errcor('You are not logged in!');
//       err.status = 403;
//       next(err);
//     }
//   });
// GET /logout
router.get("/logout", function (req, res, next) {
  // delete session object
  req.session.destroy(function (err) {
    if (err) {
      return next(err);
    } else {
      return res.redirect("/");
    }
  });
});

router.put("/profiles", (req, res, next) => {
  const data = req.body;
  User.findByIdAndUpdate(
    req.user._id,
    { $set: data },
    { new: true, useFindAndModify: false }
  )
    .then(
      (user) => {
        res.statusCode = 200;
        res.setHeader("Content-Type", "application/json");
        res.json(user);
      },
      (err) => next(err)
    )
    .catch((err) => next(err));
});

module.exports = router;
