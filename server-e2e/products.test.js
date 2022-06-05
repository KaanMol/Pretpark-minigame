const axios = require("axios").default;
axios.defaults.validateStatus = () => true;

describe("GET /products", () => {
    it("Return all products when no productId is specified", async () => {
        let res = await axios.get(`${global.ENDPOINT}/products`);

        expect(res.status).toBe(200);
        expect(res.data.length).toBeGreaterThan(0);
    });

    it("Return a single product when a productId is specified", async () => {
        let res = await axios.get(`${global.ENDPOINT}/products?id=cola`);

        expect(res.status).toBe(200);
        expect(res.data.productId).toBe("cola");
        expect(res.data.name).toBe("Cola");
    });

    it("Return Dutch description when using the languague=dutch paramater", async () => {
        let res = await axios.get(`${global.ENDPOINT}/products?id=cola&language=dutch`);

        expect(res.status).toBe(200);
        expect(res.data.description).toContain("Verzilver");
    });

    it("Return English description when using the languague=english paramater", async () => {
        let res = await axios.get(`${global.ENDPOINT}/products?id=cola&language=english`);

        expect(res.status).toBe(200);
        expect(res.data.description).toContain("Redeem");
    });

    it("Throw an error when a productId is not found", async () => {
        let res = await axios.get(`${global.ENDPOINT}/products?id=not-a-product`);

        expect(res.status).toBe(400);
        expect(res.data.error).toBe("Product not found");
    });
});