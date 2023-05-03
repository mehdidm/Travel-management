require("dotenv").config();
const express = require("express");
const bodyParser = require("body-parser");
const twilio = require("twilio")(process.env.TWILIO_ACCOUNT_SID, process.env.TWILIO_AUTH_TOKEN);
const mongoose = require("mongoose");

const app = express();
app.use(bodyParser.json());

// Connect to MongoDB
mongoose.connect(process.env.MONGO_URI, { useNewUrlParser: true, useUnifiedTopology: true });
const db = mongoose.connection;
db.on("error", (error) => console.error(error));
db.once("open", () => console.log("Connected to MongoDB"));

// Define SMS schema
const smsSchema = new mongoose.Schema({
  to: String,
  message: String,
  createdAt: {
    type: Date,
    default: Date.now
  }
});

// Define SMS model
const Sms = mongoose.model("Sms", smsSchema);

app.post("/send-sms", async (req, res) => {
  try {
    const { to, message } = req.body;

    if (!to || !message) {
      return res.status(400).json({ message: "Missing required fields" });
    }

    // Send SMS
    const sms = await twilio.messages.create({
      body: message,
      from: process.env.TWILIO_PHONE_NUMBER,
      to
    });

    // Save SMS to MongoDB
    const newSms = new Sms({
      to,
      message
    });
    await newSms.save();

    return res.status(200).json({ message: "SMS sent successfully" });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ message: "Something went wrong" });
  }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server listening on port ${PORT}`);
});
