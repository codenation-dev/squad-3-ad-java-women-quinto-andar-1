import { Injectable } from '@angular/core';
@Injectable()
export class AuthService {
    isAuthenticated() {
        const token = sessionStorage.getItem('token');
        if (token) {
            return true;
        }
        return false;
    }
}