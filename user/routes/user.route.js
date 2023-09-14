const UserController = require('../controllers/user.controller')

module.exports = (app) => {
    // Create User
    app.post('/user',
    UserController.create),

    // Login User
    app.post('/login',
    UserController.login)
}
