const axios = require("axios").default;
axios.defaults.validateStatus = () => true;

const host = "http://127.0.0.1:8000";

beforeAll(async () => {
  await axios.post(`${host}/admin?action=reset`);
});

describe("GET /points", () => {
  it("Return an error when the accountId or nfcId paramaters are not specified", async () => {
    let res = await axios.get(`${host}/points`);
    
    expect(res.status).toBe(400);
    expect(res.data.error).toBe("Missing parameter; accountId or nfcId");
  });

  it("Return an error when the account is not registered", async () => {
    let res = await axios.get(`${host}/points?accountId=1`);
    
    expect(res.status).toBe(400);
    expect(res.data.error).toBe("Account not registered");
  });

  it("Return an error when the card is not registered", async () => {
    let res = await axios.get(`${host}/points?nfcId=1`);
    
    expect(res.status).toBe(400);
    expect(res.data.error).toBe("Card not registered to an account");
  });

  it("Return empty array when the account has no points", async () => {
    // Adding card so it can be requested in the test
    let addCard = await axios.post(`${host}/cards?accountId=12&cardNumber=test`);

    expect(addCard.status).toBe(200);

    // Actual test request, make sure it returns an empty array
    let res = await axios.get(`${host}/points?accountId=12`);

    expect(res.status).toBe(200);
    expect(res.data.wins).toEqual([]);
    expect(res.data.totalPoints).toEqual(0);
  });

  it("Return empty array when the nfc does not have any points", async () => {
    // Adding card so it can be requested in the test
    let addCard = await axios.post(`${host}/cards?accountId=13&cardNumber=test2`);

    expect(addCard.status).toBe(200);
    
    // Actual test request, gets points by NFC ID
    let res = await axios.get(`${host}/points?nfcId=test2`);

    expect(res.status).toBe(200);
    expect(res.data.wins).toEqual([]);
    expect(res.data.totalPoints).toEqual(0);
    
  });
});

describe("POST /points", () => {
  it("Register one win to an account", async () => {
    // Connecting a card to an account to add points to for the test
    let connectCard = await axios.post(`${host}/cards?accountId=14&cardNumber=test3`);

    expect(connectCard.status).toBe(200);

    // Testing if points can be added to a card
    let res = await axios.post(`${host}/points?gameId=1&nfcId=test3`);

    expect(res.status).toBe(200);
    expect(res.data.message).toBe("Points added");
  });
});
