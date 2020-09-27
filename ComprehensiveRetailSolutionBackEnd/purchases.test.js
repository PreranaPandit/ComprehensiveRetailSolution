// use the path of your model
const Purchases = require('./models/purchases');
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

describe('Add Purchase Schema test anything', () => {
//the code below is for insert testing
    it('Add Purchase testing anything', () => {
        const purchases = {
            'supplierName': 'RK Lights',
            'supplierPhone': '9851073564',
            'supplierBillNumber':'0285',
            'supplierAmount':'8500',
            'supplierStatus':'PAID',
            'supplyDate':'02/09/2020',
            'remarks':'dshadgsfadhgafdgfdasda'
        };
        
        return Purchases.create(purchases)
            .then((pro_ret) => {
                expect(pro_ret.supplierName).toEqual('RK Lights');
            });
    });

    // the code below is for delete testing





// it('to test the update', async () => {

//     return AddSales.findOneAndUpdate({_id :Object('5f7051ac0defb572d8441da9')}, {$set : {customerName:'prerana'}})
//     .then((pp)=>{
//         expect(pp.customerName).toEqual('prerana')
//     })
  
// });

it('to test the delete requests is working or not', async () => {
    const status = await Purchases.deleteMany();
    expect(status.ok).toBe(1);
});



})
