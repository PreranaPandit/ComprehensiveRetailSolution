const mongoose = require("mongoose");

const purchasesSchema = new mongoose.Schema(
  {
    
    supplierName: {
      type: String,
      required: true,
    },
    supplierPhone: {
      type: String,
      required: true,
    },
    supplierBillNumber: {
      type: String,
      required: true,
    },
    supplierAmount: {
        type: String,
        required: true,
    },
    supplierStatus: {
      type: String,
      required: true,
    },
    supllyDate: {
      type: String,
     
    },
    supplierRemarks: {
      type: String,
    },
  },
  { timestamps: true }
);

module.exports = mongoose.model("Purchases", purchasesSchema);
