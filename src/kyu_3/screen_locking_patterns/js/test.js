const { assert } = require('chai');

describe("Tests", () => {
    it("test", () => {
        assert.strictEqual(countPatternsFrom('A', 0), 0);
        assert.strictEqual(countPatternsFrom('A', 10), 0);
        assert.strictEqual(countPatternsFrom('B', 1), 1);
        assert.strictEqual(countPatternsFrom('C', 2), 5);

        assert.strictEqual(countPatternsFrom('D', 3), 37);
        assert.strictEqual(countPatternsFrom('E', 4), 256);
        assert.strictEqual(countPatternsFrom('E', 8), 23280);
    });
});
