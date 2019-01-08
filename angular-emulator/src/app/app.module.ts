import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { CoresComponent } from './cores/cores.component';
import { FormsModule } from '@angular/forms';
import { CoreDetailComponent } from './core-detail/core-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { AppRoutingModule } from './app-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { KeysComponent } from './keys/keys.component';

@NgModule({
  declarations: [
    AppComponent,
    CoresComponent,
    CoreDetailComponent,
    MessagesComponent,
    DashboardComponent,
    KeysComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
