'use strict';

module.exports = {
  async up (queryInterface, Sequelize) {
     await queryInterface.bulkInsert('contacts', [{
      image: '',
      username: 'testing',
      alamat:'testing',
      notelphone: 'testing',
      email:'testing',
      tanggalLahir: new Date(),
      jenisKelasmin: 'pria'
     }], {});
  },

  async down (queryInterface, Sequelize) {
     await queryInterface.bulkDelete('contact', null, {});
  }
};
