import codewars_test as test
from solution import regex_tic_tac_toe_win_checker

@test.describe("Fixed Tests")
def fixed_tests():
    @test.it("Some boards with winners")
    def basic_test_cases():
        for winner in ["XXX-O-O-O", "X--OOOX-X", "O--OO-XXX", "O-XOX-O-X", "OXOOXOXX-", "X-O-OOXXO", "XO--X-OOX", "X-OXOOOXX"]:
            test.assert_equals(regex_tic_tac_toe_win_checker(winner), True)

    @test.it("Some boards without winners")
    def basic_test_cases():
        for not_winner in ["XO-------", "XX-XOO---", "-XX-OO-O-", "OXO--XXO-", "OOXXXO---", "OXXX-XOO-", "OOXXX----", "XXOOXXOO-", "OXOXOX---"]:
            test.assert_equals(regex_tic_tac_toe_win_checker(not_winner), False)