/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('usuarios', (table) => {
        table.increments('id')
        table.varchar('nome').notNullable()
        table.varchar('email').notNullable()
        table.varchar('login').notNullable()
        table.varchar('senha').notNullable()


      }).then(() =>{
        console.log('Criado tabela de usuarios')
      })
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {
    return knex.schema.dropTable('usuarios').then(() => {
        console.log('Deletado a tabela de usuarios')
    })
};
