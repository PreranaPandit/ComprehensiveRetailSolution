const express = require("express");

const addsale = require("../models/addsales");
const auth = require("../auth");
// Initialize express router
const router = express.Router();

// Set default API response
router
  .route("/addingSales")
  .get((req, res, next) => {
    addsale
      .find()
      .then((addsales) => {
        res.json(addsales);
      })
      .catch((err) => next(err));
  })
  .post(auth.verifyUser, (req, res, next) => {
    addsale
      .create(req.body)
      .then((addsales) => {
        res.json(addsales);
      })
      .catch(next);
  });

router.route("/:id").get((req, res, next) => {
  addsale
    .findById(req.params.id)
    .then((addsales) => {
      res.json(addsales);
    })
    .catch(next);
});

router.route("/:salesStatus/search").get((req, res, next) => {
  addsale
    .find({ salesStatus: req.params.salesStatus })

    .then(
      (addsales) => {
        if (addsales != null) {
          res.statusCode = 200;
          res.setHeader("Content-Type", "application/json");
          res.json(addsales);
        } else {
          err = new Error("Route " + req.params.salesStatus + " not found");
          err.status = 404;
          return next(err);
        }
      },
      (err) => next(err)
    )
    .catch((err) => next(err));
});

router.delete("/deleteSales/:id", function (req, res, next) {
  //console.log(req.params.id);
  addsale
    .findByIdAndDelete(req.params.id)
    .then(
      (reply) => {
        res.statusCode = 200;
        res.setHeader("Content-Type", "application/json");
        res.json(reply);
      },
      (err) => next(err)
    )
    .catch((err) => next(err));
});

module.exports = router;
