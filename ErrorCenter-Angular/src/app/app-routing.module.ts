import { AccessDeniedComponent } from './access-denied/access-denied.component';
import { LoginComponent } from './login/login.component';
import { SuccessfulRegisterComponent } from './successful-register/successful-register.component';
import { AuthGuard } from './../auth.guard';
import { ContentComponent } from './content/content.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserRegisterComponent } from './user-register/user-register.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { ErrorViewComponent } from './error-view/error-view.component';


const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'user-register', component: UserRegisterComponent },
  { path: 'succesful-register', component: SuccessfulRegisterComponent },
  { path: 'home', component: ContentComponent, canActivate: [AuthGuard] },
  { path: 'error-view', component: ErrorViewComponent, canActivate: [AuthGuard] },
  { path: 'user-home', component: UserHomeComponent, canActivate: [AuthGuard] },
  { path: 'access-denied', component: AccessDeniedComponent },
  { path: 'login', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
