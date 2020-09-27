const express = require("express");

const purchase = require("../models/purchases");
const auth = require("../auth");
// Initialize express router
const router = express.Router();

// Set default API response
router
  .route("/addingPurchases")
  .get((req, res, next) => {
    purchase
      .find()
      .then((purchases) => {
        res.json(purchases);
      })
      .catch((err) => next(err));
  })
  .post(auth.verifyUser, (req, res, next) => {
    purchase
      .create(req.body)
      .then((purchases) => {
        res.json(purchases);
      })
      .catch(next);
  });

router.route("/:id").get((req, res, next) => {
    purchase
    .findById(req.params.id)
    .then((purchases) => {
      res.json(purchases);
    })
    .catch(next);
});

router.route("/:supplierStatus/search").get((req, res, next) => {
    purchase
    .find({ supplierStatus: req.params.supplierStatus })

    .then(
      (purchases) => {
        if (purchases != null) {
          res.statusCode = 200;
          res.setHeader("Content-Type", "application/json");
          res.json(purchases);
        } else {
          err = new Error("Route " + req.params.supplierStatus + " not found");
          err.status = 404;
          return next(err);
        }
      },
      (err) => next(err)
    )
    .catch((err) => next(err));
});

router.delete("/deletePurchases/:id", function (req, res, next) {
  //console.log(req.params.id);
  purchase
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
