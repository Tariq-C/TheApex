package theApex.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.RitualDaggerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theApex.DefaultMod;
import theApex.characters.TheApex;

import java.util.Iterator;

import static theApex.DefaultMod.makeCardPath;

public class LaelapsArrow extends AbstractDynamicCard {

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

    public static final String ID = DefaultMod.makeID(LaelapsArrow.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("SplinterArrow.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheApex.Enums.COLOR_GRAY;

    private static final int COST = 0;  // COST = 2
    private static final int UPGRADED_COST = 0; // UPGRADED_COST = 2

    private static final int DAMAGE = 12;    // DAMAGE = 16
    private static final int UPGRADE_PLUS_DMG = 5;  // UPGRADE_PLUS_DMG = 5

    private static final int PERM_DAMAGE_INC = 1;
    private static final int UPGRADE_PLUS_PERM_DMG_INC = 1;

    // /STAT DECLARATION/


    public LaelapsArrow() { // - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        this.misc = DAMAGE;
        this.baseMagicNumber = PERM_DAMAGE_INC;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = this.misc;
        this.exhaust = true;

        tags.add(DefaultMod.CustomTags.ARROW);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //AbstractDungeon.actionManager.addToBottom(
          //      new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        this.addToBot(new RitualDaggerAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber, this.uuid));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_PERM_DMG_INC);
            initializeDescription();
        }
    }
}
