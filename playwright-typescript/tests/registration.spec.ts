import { test, expect } from '@playwright/test';
import { faker } from '@faker-js/faker';
const fakeName = faker.person.fullName();
const fakeEmail = faker.internet.email();
const fakePass = faker.internet.password();
const fakeAddress = faker.location.streetAddress();
const fakeSecondaryAddress = faker.location.secondaryAddress();
const fakeZipCode = faker.location.zipCode();
const fakeNumber = faker.phone.number();

test.describe('Registration', () => {

test.beforeEach(async ({ page }) => {
    
});
    
test('Register a user', async ({ page }) => {
await page.goto('/login');
await page.getByRole('textbox', {name: 'Name'}).fill(fakeName);
await page.locator('[data-qa="signup-email"]').fill(fakeEmail);
await page.getByRole('button', {name: 'Signup'}).click();
await page.getByLabel('Mr.').check();
await page.getByRole('textbox', {name: 'Password'}).fill(fakePass);
await page.locator('#days').selectOption('20');
await page.locator('#months').selectOption('May');
await page.locator('#years').selectOption('1990');
await page.getByLabel('Sign up for our newsletter!').check();
await page.getByLabel('Receive special offers from our partners!').check();
await page.getByRole('textbox', { name: 'First name *' }).fill('fakeName');
await page.getByRole('textbox', { name: 'Last name *' }).fill(fakeName);
await page.getByRole('textbox', { name: 'Company', exact: true }).fill(fakeName);
await page.getByRole('textbox', { name: 'Address * (Street address, P.' }).fill(fakeAddress);
await page.getByRole('textbox', { name: 'Address 2' }).fill(fakeSecondaryAddress);
await page.getByLabel('Country *').selectOption('United States');
await page.getByRole('textbox', { name: 'City * Zipcode *' }).fill(fakeZipCode);
await page.getByRole('textbox', { name: 'Mobile Number *' }).fill(fakeNumber);
await page.getByRole('button', { name: 'Create Account' }).click();
await expect(page.getByText('Account Created!'));



});

    });