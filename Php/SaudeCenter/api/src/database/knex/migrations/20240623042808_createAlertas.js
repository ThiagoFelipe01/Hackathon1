/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('alertas', (table) => {
        table.increments('id').primary()

        table.integer('idoso_id').unsigned().notNullable();
        table.foreign('idoso_id').references('id').inTable('idosos').onDelete('CASCADE').onUpdate('CASCADE');

        table.text('mensagem')
        table.date('data_alerta')
        table.time('horario_alerta')

      }).then(() =>{
        console.log('Criado tabela de alertas')
      })

}

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {
    return knex.schema.dropTable('alertas').then(() => {
        console.log('Deletado a tabela de alertas')
    })
};
