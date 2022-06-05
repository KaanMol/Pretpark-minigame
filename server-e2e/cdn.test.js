const axios = require("axios").default;
axios.defaults.validateStatus = () => true;

describe(`GET /cdn`, () => {
    it('Throw an error when no paramater is specified', async () => {
        let res = await axios.get(`${global.ENDPOINT}/cdn`);

        expect(res.status).toBe(400);
        expect(res.data.error).toBe("Missing parameter 'file'");
    });

    it('Throw an error when the file is not found', async () => {
        let res = await axios.get(`${global.ENDPOINT}/cdn?file=not-a-file`);

        expect(res.status).toBe(400);
        expect(res.data.error).toBe("Resource 'not-a-file' not found");
    });

    it('Return the file when it is found', async () => {
        let res = await axios.get(`${global.ENDPOINT}/cdn?file=cola.png`);

        expect(res.status).toBe(200);
    });
});