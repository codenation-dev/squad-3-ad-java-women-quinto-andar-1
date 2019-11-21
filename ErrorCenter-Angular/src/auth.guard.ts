import { AuthService } from './auth.service';
import { Injectable } from '@angular/core'; import { CanActivate, Router } from '@angular/router';
@Injectable()
export class AuthGuard implements CanActivate {
    base_url: string;
    
    constructor(private authService: AuthService, private router: Router) { }

    canActivate() {
        if (this.authService.isAuthenticated()) {
            return true;
        } else {
            this.router.navigate(['/access-denied']);
            return false;
        }
    }
}