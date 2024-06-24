/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('historico_vacinacao', (table) => {
        table.increments('id')
        table.integer('idoso_id').unsigned().notNullable();
        table.foreign('idoso_id').references('id').inTable('idosos').onDelete('CASCADE').onUpdate('CASCADE');

        table.integer('vacina_id').unsigned().notNullable();
        table.foreign('vacina_id').references('id').inTable('vacinas').onDelete('CASCADE').onUpdate('CASCADE');

        table.date('data_vacinacao')

      }).then(() =>{
        console.log('Criado tabela de historico_vacinacao')
      })
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {
    return knex.schema.dropTable('historico_vacinacao').then(() => {
        console.log('Deletado a tabela de historico_vacinacao')
    })
};
