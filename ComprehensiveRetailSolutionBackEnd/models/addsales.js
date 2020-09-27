const mongoose = require("mongoose");

const addsalesSchema = new mongoose.Schema(
  {
    customerName: {
      type: String,
      required: true,
    },
    customerPhone: {
      type: String,
      required: true,
    },
    category: {
      type: String,
      required: true,
    },
    billNumber: {
      type: String,
      required: true,
    },
    amount: {
        type: String,
        required: true,
    },
    salesStatus: {
      type: String,
      required: true,
    },
    salesDate: {
      type: String,
      required: true,
    },
    remarks: {
      type: String,
    },
  },
  { timestamps: true }
);

module.exports = mongoose.model("AddSales", addsalesSchema);
