// use the path of your model
const AddSales = require('./models/addsales');
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

describe('Add Sales Schema test anything', () => {
//the code below is for insert testing
    it('Add Sales testing anything', () => {
        const addsales = {
            'customerName': 'Pandit Prerana',
            'customerPhone': '9851173564',
            'category':'online sales',
            'billNumber':'0285',
            'amount':'8500',
            'salesStatus':'PAID',
            'salesDate':'02/09/2020',
            'remarks':'dshadgsfadhgafdgfdasda'
        };
        
        return AddSales.create(addsales)
            .then((pro_ret) => {
                expect(pro_ret.customerName).toEqual('Pandit Prerana');
            });
    });

    // the code below is for delete testing





it('to test the update', async () => {

    return AddSales.findOneAndUpdate({_id :Object('5f7051ac0defb572d8441da9')}, {$set : {customerName:'prerana'}})
    .then((pp)=>{
        expect(pp.customerName).toEqual('prerana')
    })
  
});

it('to test the delete requests is working or not', async () => {
    const status = await AddSales.deleteMany();
    expect(status.ok).toBe(1);
});



})
