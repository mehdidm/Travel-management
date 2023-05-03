const Eureka = require('eureka-js-client').Eureka;

const eureka = new Eureka({
    instance: {
        app: 'Notification-MS',
        hostName: 'localhost', // Replace this with your server IP or hostname
        ipAddr: '127.0.0.1', // Replace this with your server IP address
        port: {
            '$': 8080,
            '@enabled': 'true',
        },
        vipAddress: 'Notification-MS',
        statusPageUrl: 'http://localhost:8080/info',
        healthCheckUrl: 'http://localhost:8080/health',
        dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
            name: 'MyOwn',
        },
    },
    eureka: {
        host: 'localhost', // Replace this with your Eureka server IP or hostname
        port: 8761, // Replace this with your Eureka server port
        servicePath: '/eureka/apps/',
    },
});

eureka.logger.level('debug');

module.exports = eureka;
