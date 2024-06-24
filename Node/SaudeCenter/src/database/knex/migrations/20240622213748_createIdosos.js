/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function(knex) {
    return knex.schema.createTable('idosos', (table) => {
        table.increments('id')
        table.varchar('nome').notNullable()
        table.integer('idade').notNullable()
        table.varchar('cpf').notNullable()
        table.varchar('endereco').notNullable()
        table.varchar('telefone')
        table.text('historico_medico')
        table.text('alergias')
        table.text('condicoes_preexistentes	')
        table.text('observacoes')


      }).then(() =>{
        console.log('Criado tabela de idosos')
      })
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function(knex) {
    return knex.schema.dropTable('idosos').then(() => {
        console.log('Deletado a tabela de idosos')
    })
};
