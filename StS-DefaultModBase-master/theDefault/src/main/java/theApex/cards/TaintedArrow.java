package theApex.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theApex.TheApexMod;
import theApex.characters.TheApex;

import static theApex.TheApexMod.makeCardPath;

public class TaintedArrow extends AbstractDynamicCard {

    /*
     * "Hey, I wanna make a bunch of cards now." - You, probably.
     * ok cool my dude no problem here's the most convenient way to do it:
     *
     * Copy all of the code here (Ctrl+A > Ctrl+C)
     * Ctrl+Shift+A and search up "file and code template"
     * Press the + button at the top and name your template whatever it is for - "AttackCard" or "PowerCard" or something up to you.
     * Read up on the instructions at the bottom. Basically replace anywhere you'd put your cards name with SplinterArrow
     * And then you can do custom ones like 16 and Enemy if you want.
     * I'll leave some comments on things you might consider replacing with what.
     *
     * Of course, delete all the comments and add anything you want (For example, if you're making a skill card template you'll
     * likely want to replace that new DamageAction with a gain Block one, and add baseBlock instead, or maybe you want a
     * universal template where you delete everything unnecessary - up to you)
     *
     * You can create templates for anything you ever want to. Cards, relics, powers, orbs, etc. etc. etc.
     */

    // TEXT DECLARATION

    public static final String ID = TheApexMod.makeID(TaintedArrow.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("SplinterArrow.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheApex.Enums.COLOR_GRAY;

    private static final int COST = 0;  // COST = 2
    private static final int UPGRADED_COST = 0; // UPGRADED_COST = 2

    private static final int DAMAGE = 3;    // DAMAGE = 16
    private static final int UPGRADE_PLUS_DMG = 4;  // UPGRADE_PLUS_DMG = 5

    private static final int DRAIN = 8;

    // /STAT DECLARATION/


    public TaintedArrow() { // - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = DRAIN;
        this.exhaust = true;
        tags.add(TheApexMod.CustomTags.ARROW);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
        this.addToBot(new ApplyPowerAction(m, p, new LoseStrengthPower(m, -this.magicNumber), -this.magicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
