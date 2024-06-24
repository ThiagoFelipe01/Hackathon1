/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('agendamentos', (table) => {
        table.increments('id')
        table.integer('idoso_id').unsigned().notNullable();
        table.foreign('idoso_id').references('id').inTable('idosos').onDelete('CASCADE').onUpdate('CASCADE');

        table.date('data_agendamento')
        table.time('horario')

      }).then(() =>{
        console.log('Criado tabela de agendamentos')
      })
}

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {
    return knex.schema.dropTable('agendamentos').then(() => {
        console.log('Deletado a tabela de agendamentos')
    })
};
