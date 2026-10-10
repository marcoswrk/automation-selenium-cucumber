import type { Page, Locator } from '@playwright/test';
import { BasePage } from './BasePage.js';
import dotenv from 'dotenv';
import { getPassword, getUsedEmail } from '../utils/credentials.js';
dotenv.config();

export class LoginPage extends BasePage {
    private readonly emailInput: Locator;
    private readonly passwordInput: Locator;
    private readonly loginButton: Locator;
    private readonly logoutButton: Locator;
    private readonly loginErrorText: Locator;
    

    constructor(page: Page) {
        super(page);
        this.emailInput = page.locator('[data-qa="login-email"]');
        this.passwordInput = page.locator('[data-qa="login-password"]');
        this.loginButton = page.getByRole('button', { name: 'Login' });
        this.logoutButton = page.getByRole('link', { name: 'Logout' }); 
        this.loginErrorText = page.getByText('Your email or password is incorrect!')
    }

    async startLogin() {
        await this.page.goto('/login');
        await this.baseFill(this.emailInput, getUsedEmail());
        await this.baseFill(this.passwordInput, getPassword());
        await this.baseClick(this.loginButton);
    }

    async wrongLogin() {
        await this.page.goto('/login');
        await this.baseFill(this.emailInput, 'teste@teste.com');
        await this.baseFill(this.passwordInput, 'wrongpassword');
        await this.baseClick(this.loginButton);
    }

    async startLogout() {
        await this.page.goto('/login');
        await this.baseFill(this.emailInput, getUsedEmail());
        await this.baseFill(this.passwordInput, getPassword());
        await this.baseClick(this.loginButton);
        await this.baseClick(this.logoutButton);
    }

    async expectLoginSuccess() {
        await this.baseExpectVisible(this.logoutButton);
    }

    async expectLoginFailure() {
        await this.baseExpectVisible(this.loginErrorText);
    }

    async expectLogoutSuccess() {
        await this.baseExpectVisible(this.loginButton);
    }
}