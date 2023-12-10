export class User {
    id: number | null;
    email: string | null;
    name: string | null;
    password: string | null;
    telephone: string | null;
    dni: string | null;
    rol: string | null;
    block:boolean| null;

    constructor() {
        this.id = null;
        this.email = null;
        this.name = null;
        this.password = null;
        this.telephone = null;
        this.dni = null;
        this.rol=null;
        this.block=null;
    }
}