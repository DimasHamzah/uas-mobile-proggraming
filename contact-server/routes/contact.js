const express = require('express');
const router = express.Router();
const { addContact, getAllContact, getDetailContact, updateContactUser, deleteContactUser } = require('../controllers/ContactController');
const { uploadMiddleware } = require('../middleware/multer');

router.get('/contact', getAllContact);
router.get('/contact/:id', getDetailContact);
router.post('/contact', uploadMiddleware.single('image'), addContact);
router.put('/contact/:id', uploadMiddleware.single('image'), updateContactUser);
router.delete('/contact/:id', deleteContactUser);

module.exports = router;
