// https://www.codewars.com/kata/54bd6b4c956834c9870001a1/train/csharp

using System.Reflection;

namespace Solution {
    class BagelSolver {
        public static Bagel Bagel {
            get {
                Bagel b = new Bagel();
                b.GetType().GetProperties()[0].SetValue(b,4);
                return b;
            }
        }
    }
}
