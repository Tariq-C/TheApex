package theApex.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theApex.TheApexMod;
import theApex.util.TextureLoader;

import static theApex.TheApexMod.makePowerPath;

//Gain 1 dex for the turn for each card played.

    public class BaitTrapPowerV extends AbstractPower implements CloneablePowerInterface {
        public AbstractCreature source;

        public static final String POWER_ID = TheApexMod.makeID("BaitTrapPower");
        private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        public static final String NAME = powerStrings.NAME;
        public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

        // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
        // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
        private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
        private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

        public BaitTrapPowerV(final AbstractCreature owner, final AbstractCreature source, final int amount) {
            name = NAME;
            ID = POWER_ID;

            this.owner = owner;
            this.amount = amount;
            this.source = source;

            type = PowerType.DEBUFF;
            isTurnBased = true;

            // We load those textures here.
            this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
            this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

            updateDescription();
        }

        // On use card, apply (amount) of Dexterity. (Go to the actual power card for the amount.)
        @Override
        public void onUseCard(final AbstractCard card, final UseCardAction action) {
            //Nothing happens when the power is first applied
        }

        // At the end of the turn, remove gained Dexterity.
        @Override
        public void atEndOfTurn(final boolean isPlayer) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(source, owner, new VulnerablePower(owner, 2, false))
            );
        }

        // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
        @Override
        public void updateDescription() {
            if (amount == 1) {
                description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
            } else if (amount > 1) {
                description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
            }
        }

        @Override
        public AbstractPower makeCopy() {
            return new BaitTrapPowerV(owner, source, amount);
        }
    }

