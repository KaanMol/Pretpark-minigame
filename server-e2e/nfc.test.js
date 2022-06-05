const axios = require("axios").default;
axios.defaults.validateStatus = () => true;

describe('GET /nfc', () => {
    it("Throw an error when no paramaters are specified", async () => {
        let res = await axios.get(`${global.ENDPOINT}/nfc`);

        expect(res.status).toBe(400);
        expect(res.data.error).toBe("Missing parameter; nfcId or cardNumber");
    });

    it("Throw an error when the nfcId is not registered", async () => {
        let res = await axios.get(`${global.ENDPOINT}/nfc?nfcId=not-a-nfc-id`);

        expect(res.status).toBe(400);
        expect(res.data.error).toBe("No cardNumber found for nfcId 'not-a-nfc-id'");
    });

    it("Throw an error when the cardNumber is not registered", async () => {
        let res = await axios.get(`${global.ENDPOINT}/nfc?cardNumber=not-a-card-number`);

        expect(res.status).toBe(400);
        expect(res.data.error).toBe("No nfcId found for cardNumber 'not-a-card-number'");
    });

    it("Return the nfcId when it is registered", async () => {
        let res = await axios.get(`${global.ENDPOINT}/nfc?cardNumber=test1`);

        expect(res.status).toBe(200);
        expect(res.data.message).toBe("test1");
    });

    it("Return the cardNumber when it is registered", async () => {
        let res = await axios.get(`${global.ENDPOINT}/nfc?nfcId=test2`);

        expect(res.status).toBe(200);
        expect(res.data.message).toBe("test2");
    });
});