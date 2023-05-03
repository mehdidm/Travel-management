const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const twilio = require('twilio');
const Sms = require('../models/sms');

// Initialize Twilio client
const accountSid = process.env.TWILIO_ACCOUNT_SID;
const authToken = process.env.TWILIO_AUTH_TOKEN;
const client = twilio(accountSid, authToken);

// Send an SMS message and save to database
router.post('/', (req, res) => {
  const { number, message } = req.body;

  client.messages.create({
    to: number,
    from: process.env.TWILIO_PHONE_NUMBER,
    body: message
  })
  .then(() => {
    const sms = new Sms({ number, message });
    sms.save()
    .then(() => {
      res.status(201).json({ success: true, message: 'SMS sent and saved successfully' });
    })
    .catch((err) => {
      res.status(500).json({ success: false, message: 'Error saving SMS to database', error: err });
    });
  })
  .catch((err) => {
    res.status(500).json({ success: false, message: 'Error sending SMS', error: err });
  });
});

module.exports = router;
