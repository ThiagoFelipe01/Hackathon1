/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('vacinas', (table) => {
        table.increments('id')
        table.varchar('nome').notNullable()
        table.text('descricao')
        table.integer('intervalo_recomendado')


      }).then(() =>{
        console.log('Criado tabela de vacinas')
      })
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {
    return knex.schema.dropTable('vacinas').then(() => {
        console.log('Deletado a tabela de vacinas')
    })
};
