const express = require('express');
const bodyParser = require('body-parser');
const db = require('./config/db');
const Eureka = require('eureka-js-client').Eureka;

const app = express();
const PORT = process.env.PORT || 3000;

// Parse incoming requests with JSON payloads
app.use(bodyParser.json());

// Define the routes
const smsRoutes = require('./routes/sms');
app.use('/sms', smsRoutes);

// Create a new instance of Eureka client
const client = new Eureka({
  instance: {
    app: 'Notification-MS',
    hostName: 'localhost',
    ipAddr: '127.0.0.1',
    port: {
      '$': PORT,
      '@enabled': 'true',
    },
    vipAddress: 'Notification-MS',
    dataCenterInfo: {
      '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
      name: 'MyOwn',
    },
  },
  eureka: {
    host: 'localhost',
    port: 8761,
    servicePath: '/eureka/apps/',
    registerWithEureka: true,
    fetchRegistry: true,
  },
});

// Start the Eureka client
client.start(error => {
  if (error) {
    console.log('Eureka registration failed: ', error);
  } else {
    console.log('Eureka registration successful');
  }
});

// Start the server
app.listen(PORT, () => console.log(`Server started on port ${PORT}`));
