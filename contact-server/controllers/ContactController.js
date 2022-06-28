const { Contact } = require('../db/models');

module.exports = {
  addContact: async (req, res, next) =>{
    try {
      const { username, alamat, notelphone,email, tanggalLahir, jenisKelasmin } = req.body;
      if(!req.file) {
        return res.status(402).json({
          error: true,
          message: 'tidak adafile'
        })
      }
      const contactUser = await Contact.create({
        image: `uploads/${req.file.filename}`, username, alamat, notelphone,email, tanggalLahir, jenisKelasmin
      });
      res.status(200).json({
        error: false,
        message: 'success tambah contact',
        data: contactUser
      })
    }catch (error) {
      res.status(500).json({
        error: true,
        message: error,
        data: null
      });
      next(error)
    }
  },
  
  getDetailContact: async (req, res, next) => {
    try {
      const id = req.params.id;
      const contactUser = await Contact.findOne({
        where: { id: id}
      });
      
      if(contactUser){
        res.status(200).json({
          error: false,
          message:'ok',
          data: contactUser
        })
      }else{
        return res.status(404).json({
          error: true,
          message: 'id not found',
          data: null
        })
      }
    }catch (error) {
      res.status(500).json({
        error: true,
        message: error,
        data: null
      });
      next(error)
    }
  },
  
  getAllContact: async (req, res, next) => {
    try {
      const contactUser = await Contact.findAll({
        order: [
          ['id', 'DESC']
        ]
      });
      if (contactUser === [] || contactUser === null) {
        return res.status(422).json({
          error: true,
          message: 'data kosong',
          data: null
        })
      } else {
        res.status(200).json({
          error: false,
          message: 'success ambil data',
          data: contactUser
        });
      }
    }catch (error) {
        res.status(500).json({
          error: true,
          message: error,
          data: null
        });
        next(error)
      }
  },
  
  updateContactUser: async (req, res, next) => {
    try {
      const { username, alamat, notelphone,email, tanggalLahir, jenisKelasmin } = req.body;
      const id = req.params.id;
      const checkIdContact = await Contact.findOne({
        where: { id: id}
      });
      
      if(!req.file){
        return res.status(403).json({
          error: true,
          message: 'image tidak ada'
        })
      }
      
      if(!checkIdContact) {
        return res.status(404).json({
          error: true,
          message: 'id tidak ada',
          data: null
        })
      }else{
        const contact = await checkIdContact.update({
          image:`uploads/${req.file.filename}`, username, alamat, notelphone,email, tanggalLahir, jenisKelasmin
        });
        res.status(200).json({
          error: false,
          message: 'data terupdate',
          data: contact
        })
      }
    }catch (error) {
      res.status(500).json({
        error: true,
        message: error,
        data: null
      });
      next(error);
    }
  },
  
  deleteContactUser: async (req, res, next) => {
    try {
      const id = req.params.id;
      const checkIdContactUser = await Contact.findOne({
        where: { id : id}
      });
      if(!checkIdContactUser) {
        return res.status(404).json({
          error: true,
          message: 'id tidak ada',
          data: null
        })
      }
      const contact = await checkIdContactUser.destroy();
      res.status(200).json({
        error: false,
        message: 'contact berhasil dihapus',
        data: contact
      })
    }catch (error) {
      res.status(500).json({
        error: true,
        message: error,
        data: null
      });
      next(error);
    }
  }
};