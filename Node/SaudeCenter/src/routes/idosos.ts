import Router, {Request, Response} from "express"
import knex from "../database/knex"
import AppError from '../utils/AppError';

const routes = Router()

routes.post("/", (req, res) => {
    const objSalvar = req.body;

    knex('idosos').insert(objSalvar)
    .then(() => {
        res.json({message: "idoso Salvo"})
    })

})

routes.get("/", (req, res) => {
    knex('idosos').then((resposta) => {
        res.json({idosos: resposta})
    })
})

routes.put("/:id", async (req: Request, res: Response) =>{
    const objSalvar = req.body;

    const {id} = req.params

    let idoso = await knex('idosos').where({id}).first()


    if(!idoso){
        throw new AppError('idoso não encontrado')
    }

    let newIdoso = {
        ...idoso,
        ...objSalvar
    }

    await knex('idosos').update(newIdoso)
    .where({id: idoso.id})

    return res.json({
        message: 'Usuario alterado com Sucesso'
    })

})

routes.delete('/:id', async (req, res) => {
    const { id } = req.params

    let idoso = await knex('idosos').where({id}).first()

    if(!idoso){
        throw new AppError('Idoso não encontrado', 404)
    }

    await knex ('idosos').where({id}).delete()

    return res.json({message: "Idoso deletado com sucesso"})
})
export default routes
