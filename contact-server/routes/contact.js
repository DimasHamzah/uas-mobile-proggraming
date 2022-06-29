const express = require('express');
const router = express.Router();
const { addContact, getAllContact, getDetailContact, updateContactUser, deleteContactUser } = require('../controllers/ContactController');

router.get('/contact', getAllContact);
router.get('/contact/:id', getDetailContact);
router.post('/contact', addContact);
router.put('/contact/:id', updateContactUser);
router.delete('/contact/:id', deleteContactUser);

module.exports = router;
