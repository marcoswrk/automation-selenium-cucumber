import type { Page, Locator } from '@playwright/test';
import { BasePage } from './BasePage.js';

export interface SignupUser {
  name: string;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  company: string;
  address: string;
  address2: string;
  city: string;
  zipCode: string;
  mobileNumber: string;
  state: string;
}

export class SignupPage extends BasePage {
    private readonly nameInput: Locator;
    private readonly emailInput: Locator;
    private readonly signupButton: Locator;
    private readonly titleMr: Locator;
    private readonly passwordInput: Locator;
    private readonly firstNameInput: Locator;
    private readonly lastNameInput: Locator;
    private readonly companyInput: Locator;
    private readonly addressInput: Locator;
    private readonly address2Input: Locator;
    private readonly countrySelect: Locator;
    private readonly stateInput: Locator;
    private readonly cityInput: Locator;
    private readonly zipcodeInput: Locator;
    private readonly mobileNumberInput: Locator;
    private readonly createAccountButton: Locator;
    private readonly accountCreatedText: Locator;

    constructor(page: Page) {
        super(page);
        this.nameInput = page.getByRole('textbox', { name: 'Name' });
        this.emailInput = page.locator('[data-qa="signup-email"]');
        this.signupButton = page.getByRole('button', { name: 'Signup' });
        this.titleMr = page.getByLabel('Mr.');
        this.passwordInput = page.locator('[data-qa="password"]');
        this.firstNameInput = page.locator('[data-qa="first_name"]');
        this.lastNameInput = page.locator('[data-qa="last_name"]');
        this.companyInput = page.locator('[data-qa="company"]');
        this.addressInput = page.locator('[data-qa="address"]');
        this.address2Input = page.locator('[data-qa="address2"]');
        this.countrySelect = page.locator('[data-qa="country"]');
        this.stateInput = page.locator('[data-qa="state"]');
        this.cityInput = page.locator('[data-qa="city"]');
        this.zipcodeInput = page.locator('[data-qa="zipcode"]');
        this.mobileNumberInput = page.locator('[data-qa="mobile_number"]');
        this.createAccountButton = page.getByRole('button', { name: 'Create Account' });
        this.accountCreatedText = page.getByText('Account Created!');
    }


    async startSignup(user: SignupUser) {
        await this.page.goto('/login');
        await this.baseFill(this.nameInput, user.name);
        await this.baseFill(this.emailInput, user.email);
        await this.baseClick(this.signupButton);
    }

    async fillAccountInfo(user: SignupUser) {
        await this.baseClick(this.titleMr);
        await this.baseFill(this.passwordInput, user.password);
        await this.page.locator('#days').selectOption('20');
        await this.page.locator('#months').selectOption('May');
        await this.page.locator('#years').selectOption('1990');
    }

    async fillAddressInfo (user: SignupUser) {
        await this.baseFill(this.firstNameInput, user.firstName);
        await this.baseFill(this.lastNameInput, user.lastName);
        await this.baseFill(this.companyInput, user.company);
        await this.baseFill(this.addressInput, user.address);
        await this.baseFill(this.address2Input, user.address2);
        await this.countrySelect.selectOption('United States');
        await this.baseFill(this.stateInput, user.state);
        await this.baseFill(this.cityInput, user.city);
        await this.baseFill(this.zipcodeInput, user.zipCode);
        await this.baseFill(this.mobileNumberInput, user.mobileNumber);
        await this.baseClick(this.createAccountButton);
        
    }

    async expectAccountCreated () {
        await this.baseExpectVisible(this.accountCreatedText);
    }




}