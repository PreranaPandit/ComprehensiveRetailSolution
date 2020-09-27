// use the path of your model
const Users = require('./models/users');
const mongoose = require('mongoose');
// use the new name of the database
const url = 'mongodb://localhost:27017/test_comprehensiveretailsolution'; 
beforeAll(async () => {
    await mongoose.connect(url, {
        useNewUrlParser: true,
        useCreateIndex: true
    });
});

afterAll(async () => {

    await mongoose.connection.close();
});

describe('Add Users Schema test anything', () => {
//the code below is for insert testing
    it('Add Users testing anything', () => {
        const users = {
            'businessName': 'National Store',
            'fullName': 'Jyoti Lama Magar',
            'gender':'Female',
            'country':'Nepal',
            'contactNumber':'9851023652',
            'address':'Kathmandu',
            'emailID':'emailaddreess@gmail.com',
            'password':'123456'
        };
        
        return Users.create(users)
            .then((pro_ret) => {
                expect(pro_ret.businessName).toEqual('National Store');
            });
    });

    // the code below is for delete testing





// it('to test the update', async () => {

//     return AddSales.findOneAndUpdate({_id :Object('5f7051ac0defb572d8441da9')}, {$set : {customerName:'prerana'}})
//     .then((pp)=>{
//         expect(pp.customerName).toEqual('prerana')
//     })
  
// });

it('to test the delete uses is working or not', async () => {
    const status = await Users.deleteMany();
    expect(status.ok).toBe(1);
});



})
