import {Router} from "express"

import idoso from './idosos'
import vacina from './vacinas'
import agendamento from './agendamento'
import historico from './historicovacinacao'
import alerta from './alertas'
import usuario from './usuarios'

const routes = Router()

routes.use('/idosos',  idoso)
routes.use('/vacinas',  vacina)
routes.use('/agendamentos',  agendamento)
routes.use('/historico_vacinacao',  historico)
routes.use('/alertas',  alerta)
routes.use('/usuarios',  usuario)

export default routes
