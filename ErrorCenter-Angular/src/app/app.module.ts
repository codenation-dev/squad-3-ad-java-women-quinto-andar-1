import { AuthGuard } from './../auth.guard';
import { AuthService } from './../auth.service';

import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import {  MatDialogModule } from '@angular/material/dialog';
import { AppRoutingModule } from './app-routing.module';
import {MatTableModule} from '@angular/material/table';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatSnackBarModule} from '@angular/material/snack-bar'; 


import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { ContentComponent } from './content/content.component';
import { FooterComponent } from './footer/footer.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { LoginComponent } from './login/login.component';
import { SuccessfulRegisterComponent } from './successful-register/successful-register.component';
import { AccessDeniedComponent } from './access-denied/access-denied.component';
import { ModalNotRegisteredComponent } from './modal-not-registered/modal-not-registered.component';
import { ErrorViewComponent } from './log-view/log-view.component';
import { LogListingComponent } from './log-listing/log-listing.component';
import { HttpClientModule } from '@angular/common/http';
import { MatIconModule, MatCardModule } from '@angular/material';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ContentComponent,
    FooterComponent,
    UserRegisterComponent,
    LoginComponent,
    SuccessfulRegisterComponent,
    AccessDeniedComponent,
    ModalNotRegisteredComponent,
    ErrorViewComponent,
    LogListingComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatDialogModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatTableModule,
    MatCheckboxModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatInputModule,
    MatSelectModule,
    HttpClientModule,
    MatSnackBarModule,
    MatIconModule,
    MatCardModule    
  ],
  providers: [AuthGuard, AuthService],
  entryComponents: [ModalNotRegisteredComponent,SuccessfulRegisterComponent],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
