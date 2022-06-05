const axios = require("axios").default;
axios.defaults.validateStatus = () => true;

beforeAll(async () => {
  await axios.post(`${global.ENDPOINT}/admin?action=reset`);
});

describe("POST /cards", function () {
  it("Register a card to an account", async () => {
    let res = await axios.post(`${global.ENDPOINT}/cards?accountId=1&cardNumber=test1`);

    expect(res.status).toBe(200);
    expect(res.data.accountId).toBe("1");
    expect(res.data.nfcId).toBe("test1");
  });

  it("Return an error when a card is already registered", async () => {
    let res = await axios.post(`${global.ENDPOINT}/cards?accountId=1&cardNumber=test1`);

    expect(res.status).toBe(400);
    expect(res.data.error).toBe("Card already registered");
  });
});

describe("GET /cards", function () {
  it("Return an error when account ID is not registered", async () => {
    let res = await axios.get(`${global.ENDPOINT}/cards?accountId=100`);

    expect(res.status).toBe(400);
    expect(res.data.error).toBe("Account not registered");
  });

  it("Get array with 1 card by account ID", async () => {
    let res = await axios.get(`${global.ENDPOINT}/cards?accountId=1`);

    expect(res.status).toBe(200);
    expect(res.data).toHaveLength(1); 
    expect(res.data[0].nfcId).toBe("test1");
    expect(res.data[0].accountId).toBe("1");
  });

  it("Get array with 2 cards by account ID", async () => {
    await axios.post(`${global.ENDPOINT}/cards?accountId=1&cardNumber=test2`);

    let res = await axios.get(`${global.ENDPOINT}/cards?accountId=1`);

    expect(res.status).toBe(200);
    expect(res.data).toHaveLength(2); 
    expect(res.data[0].nfcId).toBe("test1");
    expect(res.data[0].accountId).toBe("1");

    expect(res.data[1].nfcId).toBe("test2");
    expect(res.data[1].accountId).toBe("1");
  });

  it("Return an eror when the NFC ID is not registered", async () => {
    let res = await axios.get(`${global.ENDPOINT}/cards?nfcId=100`);

    expect(res.status).toBe(400);
    expect(res.data.error).toBe("Card not registered to an account"); 
  });

  it("Get a single card by NFC ID", async () => {
    let res = await axios.get(`${global.ENDPOINT}/cards?nfcId=test1`);

    expect(res.status).toBe(200);
    expect(res.data.accountId).toBe("1"); 
    expect(res.data.nfcId).toBe("test1"); 
  });
});
