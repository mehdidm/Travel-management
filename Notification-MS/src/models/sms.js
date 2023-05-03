const mongoose = require('mongoose');

// Define the SMS schema
const smsSchema = new mongoose.Schema({
  number: { type: String, required: true },
  message: { type: String, required: true },
  date: { type: Date, default: Date.now }
});

// Define the SMS model
const Sms = mongoose.model('Sms', smsSchema);

module.exports = Sms;
