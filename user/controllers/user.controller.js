const model = require("../models")
const User = model.user

exports.create = (req, res) => {
    console.log(req.body)
    userObj = req.body

    try {
        let newUser = new User(userObj)
        newUser.save().then(()=> {
            return newUser.createSession()
        }).then((refreshToken) => {
            // Session created successfully - refreshToken returned.
            // now we generate an access auth token for the user
            return newUser.generateAccessAuthToken().then((accessToken) => {
                /* access auth token generated successfully, now we return 
                   an object containing the auth tokens
                */
                return { accessToken, refreshToken }
            })
        }).then((authTokens) => {
            /* Now we construct and send the response to the user with their 
               auth tokens in the header and the user object in the body
            */
           console.log(authTokens.refreshToken)
           console.log(authTokens.accessToken)
           res
            .header('x-refresh-token', authTokens.refreshToken)
            .header('x-access-token', authTokens.accessToken)
            .send(newUser);

        }).catch(err => {
            res.status(500).send({
                code: err.code,
                message: err.message
            })
        })
    } catch (error) {
        res.status(500).send({
            error: error
        })
    }
}

exports.login = (req, res) => {
    let email = req.body.email
    let password = req.body.password

    try {
        User.findByCredentials(email, password).then((user) => {
            return user.createSession().then((refreshToken) => {
                // Session created successfully - refreshToken returned.
                // now we generate an access auth token for the user

                return user.generateAccessAuthToken().then((accessToken) => {
                    /* access auth token generated successfully, now we return 
                        an object containing the auth tokens
                    */
                    return { accessToken, refreshToken }
                })
            }).then((authTokens) => {
                /* Now we construct and send the response to the user with their 
                    auth tokens in the header and the user object in the body
                */
                res
                    .header('x-refresh-token', authTokens.refreshToken)
                    .header('x-access-token', authTokens.accessToken)
                    .send(user);
            }).catch(err => {
                res.status(500).send({
                    message: err
                })
            })
        }).catch(err => {
            res.status(401).send({
                message: 'User Credentials invalid' || err
            })
        })
    } catch (error) {
        res.status(500).send({
            error: error
        })
    }
}