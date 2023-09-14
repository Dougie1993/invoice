const model = require("../models")
const User = model.user
const jwt = require('jsonwebtoken')

// Only request with valid JWt token will process
exports.authenticate = (req, res, next) => {
    let token = req.header('x-access-token');

    // Verify JWT
    jwt.verify(token, User.getJWTSecret(), (err, decoded) => {
        if(err) {
            // check error
            //jwt is invalid - * DO NOT AUNTENTICATE *
            res.status(401).send(err);
        } else {
            /*
                jwt is valid
                user id is encoded with the secret when 
                token was generated so we decode it here
            */
            req.user_id = decoded._id; 
            next();
        }
    });
}