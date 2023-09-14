const path = require('path')
const express = require('express')
const dotenv = require('dotenv')
const morgan = require('morgan')
const connectDB = require('./config/db')
const bodyParser = require("body-parser");
const Eureka = require('eureka-js-client').Eureka;

// Load Config
dotenv.config({path: './config/config.env'})

const app = express()

// Logging
if (process.env.NODE_ENV === 'development') {
    app.use(morgan('dev'))
}

// parse requests of content-type - application/json
app.use(bodyParser.json());
// parse requests of content-type - application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }));

/*Enable Cors */
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*"); // update to match the domain you will make the request from
  res.header("Access-Control-Allow-Methods", "GET, POST, HEAD, OPTIONS, PUT, PATCH, DELETE");
  res.header("Access-Control-Allow-Headers", "*");
  res.header("Access-Control-Expose-Headers", "x-access-token, x-refresh-token, _id");
  if (req.method === 'OPTIONS') {
      res.header('Access-Control-Allow-Methods', 'PATCH, PUT, GET,POST, DELETE');
      return res.status(200).json({});
  }
  next();
});

//routes
require('./routes/user.route')(app)

// Connection
const PORT = process.env.PORT || 5000

// Eureka Configuration
const eureka = new Eureka({
    instance: {
      app: 'USER-SERVICE', // Set the microservice name here
      hostName: `Dougie.Station: ${PORT} `, // microservice hostname
      ipAddr: '127.0.0.1', //  microservice IP address
      port: {
        $: 3000, //  microservice port
        '@enabled': true,
      },
      vipAddress: 'USER-SERVICE',
      dataCenterInfo: {
        '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
        name: 'MyOwn',
      },
    },
    eureka: {
      host: 'localhost', // Eureka server hostname
      port: 8761, // Eureka server port
      servicePath: '/eureka/apps/',
      maxRetries: 10,
      requestRetryDelay: 2000,
    },
  });
  eureka.start((error) => {
    if (error) {
      console.log('Eureka registration failed!');
    } else {
      console.log('Eureka registered and connected!');
    }
  });

  

connectDB().then(
    app.listen(PORT, () => {
        console.log(`Server running in ${process.env.NODE_ENV} mode on port:${PORT}`)
    })
).catch(err => {
    console.error('unable to connect ', err.message)
})