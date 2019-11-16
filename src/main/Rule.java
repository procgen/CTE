package main;

import java.util.HashMap;
import static main.Symbol.*;

public class Rule {

    private static HashMap<Integer, Rule> rules = new HashMap<>();

    static {
        rules.put(1, new Rule(PGM, KPROG, MAIN));
        rules.put(2, new Rule(MAIN, KMAIN, BBLOCK));
        rules.put(3, new Rule(BBLOCK, BRACE1, VARGROUP, STMTS, BRACE2));

       	rules.put(4, new Rule(VARGROUP, KVAR, PPVARLIST));
        rules.put(5, new Rule(VARGROUP));
        rules.put(6, new Rule(PPVARLIST, PARENS1, VARLIST, PARENS2));
        rules.put(7, new Rule(VARLIST, VARITEM, SEMI, VARLIST));
        rules.put(8, new Rule(VARLIST));

        // modified for simple rule set
        rules.put(12, new Rule(VARDECL, BASEKIND, VARSPEC));
//        rules.put(12, new Rule(VARDECL, SIMPLEKIND, VARSPEC));

        rules.put(13, new Rule(SIMPLEKIND, BASEKIND));
        rules.put(14, new Rule(SIMPLEKIND, CLASSID));
        rules.put(15, new Rule(BASEKIND, KINT));
        rules.put(16, new Rule(BASEKIND, KFLOAT));
        rules.put(17, new Rule(BASEKIND, KSTRING));
        rules.put(18, new Rule(CLASSID, ID));
        rules.put(21, new Rule(VARSPEC, DEREF_ID));
        rules.put(22, new Rule(VARID, ID));
        rules.put(23, new Rule(ARRSPEC, VARID, KKINT));
        rules.put(24, new Rule(KKINT, BRACKET1, INT, BRACKET2));
        rules.put(25, new Rule(DEREF_ID, DEREF, ID));
        rules.put(26, new Rule(DEREF, ASTER));

        rules.put(27, new Rule(VARINIT, EXPR));
        rules.put(28, new Rule(VARINIT, BBEXPRS));
        rules.put(31, new Rule(EXPRLIST, EXPR, MOREEXPRS));
        rules.put(32, new Rule(MOREEXPRS, COMMA, EXPRLIST));
        rules.put(33, new Rule(MOREEXPRS));
        rules.put(34, new Rule(CLASSDECL, KCLASS, CLASSID));

        rules.put(79, new Rule(FCALL, FCNID, PPEXPRS));

        rules.put(53, new Rule(FCNDEFS, FCNDEF, FCNDEFS));
        rules.put(54, new Rule(FCNDEFS));
        rules.put(55, new Rule(FCNDEF, FCNHEADER, BBLOCK));
        rules.put(56, new Rule(FCNHEADER, KFCN, FCNID, PPARMLIST, RETKIND));
        rules.put(57, new Rule(FCNID, ID));
        rules.put(58, new Rule(RETKIND, BASEKIND));

        rules.put(61, new Rule(VARSPECS, VARSPEC, MORE_VARSPECS));
        rules.put(62, new Rule(MORE_VARSPECS, COMMA, VARSPECS));
        rules.put(63, new Rule(MORE_VARSPECS));
        rules.put(64, new Rule(PPONLY, PARENS1, PARENS2));
        rules.put(65, new Rule(STMTS, STMT, SEMI, STMTS));
        rules.put(66, new Rule(STMTS));

        rules.put(69, new Rule(STMT, STIF));
        rules.put(70, new Rule(STMT, STWHILE));
        rules.put(71, new Rule(STMT, STPRINT));
        rules.put(72, new Rule(STMT, STRTN));

        // pretty sure this was factored out
//        rules.put(73, new Rule(STASGN, LVAL, EQUAL, EXPR));

        rules.put(76, new Rule(LVAL, DEREF_ID));
        rules.put(77, new Rule(AREF, VARID, KKEXPR));
        rules.put(78, new Rule(KKEXPR, BRACKET1, EXPR, BRACKET2));
        rules.put(79, new Rule(FCALL, FCNID, PPEXPRS));

        rules.put(82, new Rule(STIF, KIF, PPEXPR, BBLOCK, ELSEPART));

        rules.put(83, new Rule(ELSEPART, KELSEIF, PPEXPR, BBLOCK, ELSEPART));
        rules.put(84, new Rule(ELSEPART, KELSE, BBLOCK));
        rules.put(85, new Rule(ELSEPART));

        rules.put(86, new Rule(STWHILE, KWHILE, PPEXPR, BBLOCK));
        rules.put(87, new Rule(STPRINT, KPRINT, PPEXPRS));

        rules.put(90, new Rule(PPEXPR, PARENS1, EXPR, PARENS2));

        rules.put(97, new Rule(FACT, BASELITERAL));
        rules.put(99, new Rule(FACT, ADDROF_ID));
        rules.put(101, new Rule(FACT, PPEXPR));
        rules.put(102, new Rule(BASELITERAL, INT));
        rules.put(103, new Rule(BASELITERAL, FLOAT));
        rules.put(104, new Rule(BASELITERAL, STRING));
        rules.put(105, new Rule(ADDROF_ID, AMPERSAND, ID));
        rules.put(106, new Rule(OPREL, OPEQ));
        rules.put(107, new Rule(OPREL, OPNE));
        rules.put(108, new Rule(OPREL, LTHAN));
        rules.put(109, new Rule(OPREL, OPLE));
        rules.put(110, new Rule(OPREL, OPGE));
        rules.put(111, new Rule(OPREL, GTHAN));
        rules.put(112, new Rule(LTHAN, ANGLE1));
        rules.put(113, new Rule(GTHAN, ANGLE2));
        rules.put(114, new Rule(OPADD, PLUS));
        rules.put(115, new Rule(OPADD, MINUS));
        rules.put(116, new Rule(OPMUL, ASTER));
        rules.put(117, new Rule(OPMUL, SLASH));
        rules.put(118, new Rule(OPMUL, CARET));
        rules.put(119, new Rule(LVAL, VARID, DLVAL));
        rules.put(120, new Rule(DLVAL));

        rules.put(122, new Rule(FACT, new Symbol(Token.ID, true), DFACT));

        rules.put(123, new Rule(DFACT));
        rules.put(124, new Rule(DFACT, PPEXPRS));

        rules.put(125, new Rule(VARSPEC, ID, DVARSPEC));
        rules.put(126, new Rule(DVARSPEC));
        rules.put(127, new Rule(DVARSPEC, KKINT));

        rules.put(128, new Rule(PPARMLIST, PARENS1, DPPARMLIST));
        //rule 129 goes here
        rules.put(130, new Rule(DPPARMLIST, PARENS2));

        rules.put(131, new Rule(STMT, ID, DSTMT));
        rules.put(132, new Rule(DSTMT, EQUAL, EXPR));
        rules.put(133, new Rule(DSTMT, PPEXPRS));

        rules.put(134, new Rule(PPEXPRS, PARENS1, DPPEXPRS));
        rules.put(135, new Rule(DPPEXPRS, EXPRLIST, PARENS2));
        rules.put(136, new Rule(DPPEXPRS));

        rules.put(137, new Rule(VARITEM, VARDECL, DVARITEM));
        rules.put(138, new Rule(DVARITEM));
        rules.put(139, new Rule(DVARITEM, EQUAL, VARINIT));

        rules.put(140, new Rule(LEXPR, OPREL, RTERM, LEXPR));
        rules.put(141, new Rule(EXPR, RTERM, LEXPR));
        rules.put(142, new Rule(LEXPR));

        rules.put(143, new Rule(LRTERM, OPADD, TERM, LRTERM));
        rules.put(144, new Rule(RTERM, TERM, LRTERM));
        rules.put(145, new Rule(LRTERM));

        rules.put(146, new Rule(LTERM, OPMUL, FACT, LTERM));
        rules.put(147, new Rule(TERM, FACT, LTERM));
        rules.put(148, new Rule(LTERM));

        rules.put(149, new Rule(STRTN, KRETURN, DSTRTN));
        rules.put(150, new Rule(DSTRTN, EXPR));
        rules.put(151, new Rule(DSTRTN));

    }

//    private int ruleID;
    private Symbol LHS;
    private Symbol[] RHS;

    public Rule(Symbol leftHandSymbol, Symbol ... rightHandSymbols) {
        LHS = leftHandSymbol;
        RHS = new Symbol[10];
        int count = 0;
        for(Symbol r : rightHandSymbols) {
            RHS[count] = r;
            count++;
        }
    }

    public static Rule getRule(int id) {
        return rules.get(id);
    }

//    public int getRuleID() {
//        return ruleID;
//    }

    public Symbol getLHS() {
        return LHS;
    }

    public Symbol getRHS(int i) {
        return RHS[i];
    }

    public int getRHSLength() {
        return RHS.length;
    }

}