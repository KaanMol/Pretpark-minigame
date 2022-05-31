const request = require('superagent');
const agent = request.agent();

const host = 'http://localhost:8000';

beforeAll(function(done) {
    agent.post(`${host}/admin?action=reset`).then(res => {
        done();
    })
})

describe('POST /cards', function() {
    it('Register a card to an account', function(done) {
        agent
            .post(`${host}/cards?accountId=1&cardId=1`)
            .end(function(err, res) {
                expect(res.status).toBe(200);
                expect(res.body.accountId).toBe("1");
                expect(res.body.cardId).toBe("1");
                done();
            })
    });

    it('Return an error when a card is already registered', function(done) {
        agent
            .post(`${host}/cards?accountId=1&cardId=1`)
            .end(function(err, res) {
                expect(res.status).toBe(400);
                expect(res.body.error).toBe("Card already registered");
                done();
            })
    });
});

describe('GET /cards', function() {
    it('Return empty array when account ID is not registered', function (done) {
        agent
        .get(`${host}/cards?accountId=100`)
        .end(function(err, res) {
            expect(res.status).toBe(400);
            expect(res.body.error).toBe("Account not registered");
            done();
        });
    });
});
