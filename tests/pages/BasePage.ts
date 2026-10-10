import type { Page, Locator } from '@playwright/test';
import { expect } from '@playwright/test';


export abstract class BasePage {

    constructor(protected readonly page:Page) { }



    protected async baseClick (selector: string | Locator) {
        await this.toLocator(selector).click();
    }

    protected async baseFill (selector: string | Locator, value: string) {
        await this.toLocator(selector).fill(value);
    }

    protected async baseExpectVisible (selector: string | Locator) {
        await expect(this.toLocator(selector)).toBeVisible();
    }



    protected toLocator(selector: string | Locator) {
    return typeof selector === 'string'
    ? this.page.locator(selector)
    : selector;

    }

}