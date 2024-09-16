 class people{

    constructor(){
        this.peopleList = [
            {nome: 'tauan'}
        ]
    }
    addpeople(person){
        this.peopleList.push(person)
    }

    getALL(){
        return this.peopleList
    }

}

class customer extends people{
    constructor(){
        super()
        this.addpeople({nome: 'Pedro'})
    }
}

const _people = new people()
_people.addpeople({nome: 'joão'})

console.log('list:', _people.getALL())

const _customer = new customer()
_customer.addpeople({nome: 'ricardo'})

console.log('customers:', _customer.getALL())