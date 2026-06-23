namespace Solution {
    using NUnit.Framework;
    using System;

    [TestFixture]
    public class BagelTest {
        [Test]
        public void TestBagel() {
            Bagel bagel = BagelSolver.Bagel;
            NUnit.Framework.Assert.That(bagel.Value, Is.EqualTo(4));
        }
    }
}
