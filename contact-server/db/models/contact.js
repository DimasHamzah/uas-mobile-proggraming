'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Contact extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      // define association here
    }
  }
  Contact.init({
    image: DataTypes.STRING,
    username: DataTypes.STRING,
    alamat: DataTypes.STRING,
    notelphone: DataTypes.STRING,
    email: DataTypes.STRING,
    tanggalLahir: DataTypes.DATE,
    jenisKelasmin: DataTypes.ENUM(['pria','perempuan'])
  }, {
    sequelize,
    modelName: 'Contact',
  });
  return Contact;
};