'use strict';

module.exports = {
  async up (queryInterface, Sequelize) {
     await queryInterface.bulkInsert('Contacts', [{
      username: 'testing',
      alamat:'testing',
      notelphone: 'testing',
      email:'testing',
      tanggalLahir: new Date(),
      jenisKelasmin: 'pria',
      createdAt: new Date(),
      updatedAt: new Date()
     }], {});
  },

  async down (queryInterface, Sequelize) {
     await queryInterface.bulkDelete('Contact', null, {});
  }
};
