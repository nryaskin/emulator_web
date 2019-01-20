import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MaterialModule } from './material.module';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { CoresComponent } from './cores/cores.component';
import { CoreDetailComponent } from './core-detail/core-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { AppRoutingModule } from './app-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { KeysComponent } from './keys/keys.component';
import { CoreEditorComponent } from './core-editor/core-editor.component';
import { CoreEditComponent } from './core-edit/core-edit.component';
import { CoreAddComponent } from './core-add/core-add.component';
import { HeaderComponent } from './navigation/header/header.component';
import { ProfileListComponent } from './profile/profile-list/profile-list.component';
import { PaginationComponent } from './navigation/pagination/pagination.component';
import { ProfileDialogComponent } from './profile/profile-dialog/profile-dialog.component';
import { SortingComponent } from './sorting/sorting.component';

@NgModule({
  declarations: [
    AppComponent,
    CoresComponent,
    CoreDetailComponent,
    MessagesComponent,
    DashboardComponent,
    KeysComponent,
    CoreEditorComponent,
    CoreEditComponent,
    CoreAddComponent,
    HeaderComponent,
    PaginationComponent,
    ProfileListComponent,
    ProfileDialogComponent,
    SortingComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    MaterialModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [ProfileDialogComponent]
})
export class AppModule { }
